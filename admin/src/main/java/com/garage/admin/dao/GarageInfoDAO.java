package com.garage.admin.dao;

import com.garage.admin.model.GarageInfo;
import com.garage.admin.model.LoginTicket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GarageInfoDAO {
    String TABLE_NAME = "garage_info";
    String INSERT_FIELDS = " garage_code, latitude, longtitude, address, province, city, total_num, free_num, price_per_hour ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    GarageInfo selectById(int id);

    @Select({"select * from ", TABLE_NAME})
    List<GarageInfo> selectAll();

    @Select({"select * from ", TABLE_NAME, " where province=#{province} and city=#{city}"})
    List<GarageInfo> selectByArea(@Param("province") String province, @Param("city") String city);


    @Update({"update ", TABLE_NAME, " set latitude=#{latitude},longtitude=#{longtitude},address=#{address}," +
            "total_num=#{totalNum},free_num=#{freeNum},price_per_hour=#{pricePerHour} where id=#{garageId}"})
    int updateGarageInfo(@Param("garageId") int garageId,
                          @Param("address") String address,
                          @Param("totalNum") int totalNum,
                          @Param("freeNum") int freeNum,
                          @Param("latitude") float latitude,
                          @Param("longtitude") float longtitude,
                          @Param("pricePerHour") float pricePerHour);

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{latitude},#{longtitude},#{address},#{totalNum},#{freeNum},#{pricePerHour})"})
    int addGarage(GarageInfo garageInfo);

    @Select("select * from garage_info where garage_code=#{garageCode}")
    GarageInfo selectByGarageCode(String garageCode);
}
