package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.DivorceAPIRequest;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public interface DivorceService {

    /**
     * Checks role for given tax number
     *
     * @param taxNumber to search on DB
     * @param faculty   to cross-check
     * @return User instance from DB
     * @throws IllegalArgumentException when tax number does not exist in DB or when role isn't assigned
     */
    User checkRole (Integer taxNumber, Faculty faculty) throws IllegalArgumentException, UserNotFoundException, UserWithWrongRoleException;

    /**
     * Prepares divorce. Before saving application date needs to be set.
     * @param divorce
     * @return
     */
    Divorce prepareDivorce (DivorceAPIRequest divorce) throws UserNotFoundException, UserWithWrongRoleException;

    /**
     * Prepares divorce statements. Uses {@link DivorceService#prepareInvolvedParties(DivorceAPIRequest) prepareInvolvedParties } method
     *
     * @param divorce
     * @throws IllegalArgumentException if any of the involved parties is not found or does not have the correct role
     */
    List<DivorceStatement> prepareDivorceStatements(DivorceAPIRequest divorceData, Divorce divorce) throws UserNotFoundException, UserWithWrongRoleException;

    /**
     * Prepares divorce statement. Uses {@link DivorceService#prepareInvolvedParties(DivorceAPIRequest) prepareInvolvedParties } method
     *
     * @param divorce
     * @throws IllegalArgumentException if the involved party is not found or does not have the correct role
     */
    DivorceStatement prepareDivorceStatement(User user, Divorce divorce, Faculty faculty) throws UserNotFoundException, UserWithWrongRoleException;

    /**
     * Prepares involved partis after checking faculty and existence using {@link DivorceService#checkRole(Integer, Faculty) checkRole()} method
     * @param divorceData
     * @return
     * @throws IllegalArgumentException if any of the involved parties is not found or does not have the correct role
     */
    HashMap<Faculty,User> prepareInvolvedParties (DivorceAPIRequest divorceData) throws IllegalArgumentException, UserNotFoundException, UserWithWrongRoleException;

    /**
     * Checks divorce for final commit
     * @param divorce
     * @return
     * @throws IllegalArgumentException if any of the involved parties is not found or does not have the correct role, or if the divorce is not in the correct status
     */
    static Divorce checkDivorce(Divorce divorce) throws FewerDivorceStatementsException, DivorceStatusException {
        //Check if status is valid
        if (divorce.getStatus().equals(DivorceStatus.COMPLETED) || divorce.getStatus().equals(DivorceStatus.CANCELLED)) {
            throw new DivorceStatusException("Divorce status set to " + divorce.getStatus() + " is not valid.");
        }

        //Check if there are duplicate persons in statements
        if (divorce.getSpouseOne().equals(divorce.getSpouseTwo())) {
            throw new FewerDivorceStatementsException("Spouse one and spouse two can not be set the same person.");
        }
        if (divorce.getSpouseOne().equals(divorce.getNotary()) || divorce.getSpouseTwo().equals(divorce.getNotary())) {
            throw new FewerDivorceStatementsException("Spouse one and notary can not be set the same person.");
        }
        if (divorce.getLawyerLead().equals(divorce.getNotary()) || divorce.getLawyerTwo().equals(divorce.getNotary())) {
            throw new FewerDivorceStatementsException("Lawyers can not be also set as notary.");
        }

        //Check that all statements are present if status is not draft
        if (!divorce.getStatus().equals(DivorceStatus.DRAFT)) {
            List<Faculty> faculties = Faculty.getFaculties();
            for (DivorceStatement divorceStatement : divorce.getStatement()) {
                if (!faculties.contains(divorceStatement.getFaculty())) {
                    throw new FewerDivorceStatementsException("Divorce statement with faculty: " + divorceStatement.getFaculty().name() + " not found.");
                } else {
                    faculties.remove(divorceStatement.getFaculty());
                }
            };
        }
        return divorce;
    };

    /**
     * Creates new divorce
     * @param divorce
     * @return
     * @throws FewerDivorceStatementsException
     * @throws DivorceStatusException
     * @throws UserNotFoundException
     * @throws UserWithWrongRoleException
     */
    Divorce createDivorce(DivorceAPIRequest divorce) throws FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException;

    /**
     * Edits divorce
     * @param divorceData
     * @return
     * @throws DivorceStatusException
     * @throws UserNotFoundException
     * @throws UserWithWrongRoleException
     * @throws FewerDivorceStatementsException
     */
    Divorce editDivorce(DivorceAPIRequest divorceData) throws DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException;

    /**
     * Save divorce to db
     * @param divorce
     */
    Divorce saveDivorce(Divorce divorce);
}
