package nl.hsleiden.svdj8.models;

public class Statement {
    private StringBuilder createStatement;
    private StringBuilder updateStatement;
    private StringBuilder deleteStatement;
    private StringBuilder getStatement;



//    Getters Setters
    public StringBuilder getCreateStatement() {
        return createStatement;
    }

    public void setCreateStatement(StringBuilder createStatement) {
        this.createStatement = createStatement;
    }

    public StringBuilder getUpdateStatement() {
        return updateStatement;
    }

    public void setUpdateStatement(StringBuilder updateStatement) {
        this.updateStatement = updateStatement;
    }

    public StringBuilder getDeleteStatement() {
        return deleteStatement;
    }

    public void setDeleteStatement(StringBuilder deleteStatement) {
        this.deleteStatement = deleteStatement;
    }

    public StringBuilder getGetStatement() {
        return getStatement;
    }

    public void setGetStatement(StringBuilder getStatement) {
        this.getStatement = getStatement;
    }
}
