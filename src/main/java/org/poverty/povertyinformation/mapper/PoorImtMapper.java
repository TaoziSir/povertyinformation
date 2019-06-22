package org.poverty.povertyinformation.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.poverty.povertyinformation.pobj.Bookbuilding;

import java.util.List;

public interface PoorImtMapper {
    /**
     * 地区
     * @return
     */
    @Select("SELECT area from shu group by area")
    public List<String> groupArea();


    /**
     * 根据条件查询
     * @param name
     * @param area
     * @param idCard
     * @return
     */
    @Select({"<script>","SELECT name,area,identityCard,laborSkills,nation,healthCondition FROM shu",
            "WHERE 1=1","<when test='name!=null'>",
            " AND name = #{name}",
            "</when>",
            "<when test='area!=null'>",
            " AND area = #{area}",
            "</when>",
            "<when test='idCard!=null'>",
            " AND identityCard = #{idCard}",
            "</when>",
            "</script>"})
    public List<Bookbuilding> fileAll(@Param("name")String name,@Param("area")String area,@Param("idCard")String idCard);
}
