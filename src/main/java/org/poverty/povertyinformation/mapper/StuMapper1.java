package org.poverty.povertyinformation.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.poverty.povertyinformation.pobj.RecipientStu;

import java.util.List;

public interface StuMapper1 {
    @Select({"<script>",
            "SELECT name,school,address,helpedProject," +
                    "helpedmoney,helpedMessage FROM schooldata",
            "WHERE 1=1",
            " AND name = #{name}",
            " AND idCard = #{idCard}",
            "</script>"})
    public List<RecipientStu> fileAllStu(@Param("name")String name, @Param("idCard")String idCard);

}
