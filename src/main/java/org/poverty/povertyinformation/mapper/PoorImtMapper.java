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
     *
     * "SELECT name,area,identityCard,laborSkills,nation,healthCondition FROM shu",
     * @param name
     * @param idCard
     * @return
     */
    @Select({"<script>",
            "SELECT name,area,village,administrativeVillage,overcomePoverty FROM studentdat",
            "WHERE 1=1",
            " AND name = #{name}",
            " AND idCard = #{idCard}",
            "</script>"})
    public List<Bookbuilding> fileAll(@Param("name")String name,@Param("idCard")String idCard);
}
