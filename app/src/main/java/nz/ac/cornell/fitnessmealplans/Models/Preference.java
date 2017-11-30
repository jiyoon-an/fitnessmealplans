package nz.ac.cornell.fitnessmealplans.Models;

/**
 * It is a class of Preference.
 * It includes data of preference of user type
 *
 * @author Jiyoon An
 * @date 03/06/16
 *
 * @param codeId is the value of pregerence id
 * @param codeName is the value of preference name
 * @param description is detail about preference
 */
public class Preference {

    private String codeId;
    private String codeName;
    private String description;

    public Preference(String codeId, String codeName, String description) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.description = description;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
