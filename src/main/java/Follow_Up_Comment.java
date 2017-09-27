/**
 * Created by libbyjennings on 25/09/17.
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Create database table name
@DatabaseTable(tableName = "FOLLOW_UP_COMMENT")

public class Follow_Up_Comment {

    @DatabaseField
    private int CommentID;

    @DatabaseField
    private int SaleID;

    @DatabaseField
    private String Comment;

    @DatabaseField
    private String Time_Stamp;

    //Getters and setters
    public Follow_Up_Comment() {

    }

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getSaleID() {
        return SaleID;
    }

    public void setSaleID(int saleID) {
        SaleID = saleID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getTime_Stamp() {
        return Time_Stamp;
    }

    public void setTime_Stamp(String time_Stamp) {
        Time_Stamp = time_Stamp;
    }
}
