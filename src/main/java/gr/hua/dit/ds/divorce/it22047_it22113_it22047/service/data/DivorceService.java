package gr.hua.dit.ds.divorce.it22047_it22113_it22047.service.data;

import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.*;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.entity.api.DivorceAPIRequest;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.DivorceStatusException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.FewerDivorceStatementsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.divorce.SimilarDivorceExistsException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserNotFoundException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.exceptions.user.UserWithWrongRoleException;
import gr.hua.dit.ds.divorce.it22047_it22113_it22047.repositories.DivorceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public interface DivorceService {

    @Autowired
    DivorceRepository divorceRepo = null;

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
    Divorce prepare (DivorceAPIRequest divorce) throws UserNotFoundException, UserWithWrongRoleException;

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
    Divorce checkBeforePublishing (Divorce divorce) throws FewerDivorceStatementsException, DivorceStatusException, SimilarDivorceExistsException;

    /**
     * Creates new divorce
     * @param divorce
     * @return
     * @throws FewerDivorceStatementsException
     * @throws DivorceStatusException
     * @throws UserNotFoundException
     * @throws UserWithWrongRoleException
     */
    Divorce create (DivorceAPIRequest divorce) throws FewerDivorceStatementsException, DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, SimilarDivorceExistsException;

    /**
     * Edits divorce
     * @param divorceData
     * @return
     * @throws DivorceStatusException
     * @throws UserNotFoundException
     * @throws UserWithWrongRoleException
     * @throws FewerDivorceStatementsException
     */
    Divorce edit (DivorceAPIRequest divorceData) throws DivorceStatusException, UserNotFoundException, UserWithWrongRoleException, FewerDivorceStatementsException, SimilarDivorceExistsException;

    /**
     * Checks if similar divorce already exists. Ignores completed and cancelled divorces.
     *
     * @param divorce to check
     * @return similar divorce if exists, null otherwise
     */
    static Divorce checkIfDivorceExists(Divorce divorce) {
        return null;
    }

    /**
     * Deletes given divorce
     * @param divorce
     * @throws DivorceStatusException
     */
    void delete(Divorce divorce) throws DivorceStatusException;
}
