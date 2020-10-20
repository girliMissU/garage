package com.garage.admin.dao;

import com.garage.admin.model.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReplyDAO {
    String TABLE_NAME = " reply ";
    String INSERT_FIELDS = " manager, authority, to_name, question_id, content, created_date, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{manager},#{authority},#{toName},#{questionId},#{content},#{createdDate},#{status})"})
    int addReply(Reply reply);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    Reply getReplyById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME,
            " where question_id=#{questionId} order by created_date"})
    List<Reply> selectReplyByQuestionId(@Param("questionId") int questionId);

    @Update({"update", TABLE_NAME, "set status=1 where status=0 and to_name=#{toName}"})
    int updateStatus(@Param("toName") String toName);

    @Select({"select count(id) from ", TABLE_NAME, " where status=0 and to_name=#{toName}"})
    int getReplyUnreadCountByToName(@Param("toName") String toName);

}
