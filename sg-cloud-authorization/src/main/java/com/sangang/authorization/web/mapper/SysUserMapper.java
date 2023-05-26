package com.sangang.authorization.web.mapper;

import static com.sangang.authorization.web.mapper.SysUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.sangang.authorization.web.entity.SysUser;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface SysUserMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<SysUser>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, userKey, mainKey, userNo, userName, password, userFlag, userType, status, phoneNum, realName, nickname, email, avatar, birthday, startTime, endTime, genderType, ownerId, externalCode, createdTime, updatedTime, remark, qrCode, lunId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysUserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_key", property="userKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="main_key", property="mainKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_flag", property="userFlag", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_type", property="userType", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="phone_num", property="phoneNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="real_name", property="realName", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="start_time", property="startTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_time", property="endTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gender_type", property="genderType", jdbcType=JdbcType.TINYINT),
        @Result(column="owner_id", property="ownerId", jdbcType=JdbcType.VARCHAR),
        @Result(column="external_code", property="externalCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_time", property="updatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="qr_code", property="qrCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="lun_id", property="lunId", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUser> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysUserResult")
    Optional<SysUser> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SysUser row) {
        return MyBatis3Utils.insert(this::insert, row, sysUser, c ->
            c.map(id).toProperty("id")
            .map(userKey).toProperty("userKey")
            .map(mainKey).toProperty("mainKey")
            .map(userNo).toProperty("userNo")
            .map(userName).toProperty("userName")
            .map(password).toProperty("password")
            .map(userFlag).toProperty("userFlag")
            .map(userType).toProperty("userType")
            .map(status).toProperty("status")
            .map(phoneNum).toProperty("phoneNum")
            .map(realName).toProperty("realName")
            .map(nickname).toProperty("nickname")
            .map(email).toProperty("email")
            .map(avatar).toProperty("avatar")
            .map(birthday).toProperty("birthday")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(genderType).toProperty("genderType")
            .map(ownerId).toProperty("ownerId")
            .map(externalCode).toProperty("externalCode")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(remark).toProperty("remark")
            .map(qrCode).toProperty("qrCode")
            .map(lunId).toProperty("lunId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SysUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysUser, c ->
            c.map(id).toProperty("id")
            .map(userKey).toProperty("userKey")
            .map(mainKey).toProperty("mainKey")
            .map(userNo).toProperty("userNo")
            .map(userName).toProperty("userName")
            .map(password).toProperty("password")
            .map(userFlag).toProperty("userFlag")
            .map(userType).toProperty("userType")
            .map(status).toProperty("status")
            .map(phoneNum).toProperty("phoneNum")
            .map(realName).toProperty("realName")
            .map(nickname).toProperty("nickname")
            .map(email).toProperty("email")
            .map(avatar).toProperty("avatar")
            .map(birthday).toProperty("birthday")
            .map(startTime).toProperty("startTime")
            .map(endTime).toProperty("endTime")
            .map(genderType).toProperty("genderType")
            .map(ownerId).toProperty("ownerId")
            .map(externalCode).toProperty("externalCode")
            .map(createdTime).toProperty("createdTime")
            .map(updatedTime).toProperty("updatedTime")
            .map(remark).toProperty("remark")
            .map(qrCode).toProperty("qrCode")
            .map(lunId).toProperty("lunId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SysUser row) {
        return MyBatis3Utils.insert(this::insert, row, sysUser, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(userKey).toPropertyWhenPresent("userKey", row::getUserKey)
            .map(mainKey).toPropertyWhenPresent("mainKey", row::getMainKey)
            .map(userNo).toPropertyWhenPresent("userNo", row::getUserNo)
            .map(userName).toPropertyWhenPresent("userName", row::getUserName)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
            .map(userFlag).toPropertyWhenPresent("userFlag", row::getUserFlag)
            .map(userType).toPropertyWhenPresent("userType", row::getUserType)
            .map(status).toPropertyWhenPresent("status", row::getStatus)
            .map(phoneNum).toPropertyWhenPresent("phoneNum", row::getPhoneNum)
            .map(realName).toPropertyWhenPresent("realName", row::getRealName)
            .map(nickname).toPropertyWhenPresent("nickname", row::getNickname)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(avatar).toPropertyWhenPresent("avatar", row::getAvatar)
            .map(birthday).toPropertyWhenPresent("birthday", row::getBirthday)
            .map(startTime).toPropertyWhenPresent("startTime", row::getStartTime)
            .map(endTime).toPropertyWhenPresent("endTime", row::getEndTime)
            .map(genderType).toPropertyWhenPresent("genderType", row::getGenderType)
            .map(ownerId).toPropertyWhenPresent("ownerId", row::getOwnerId)
            .map(externalCode).toPropertyWhenPresent("externalCode", row::getExternalCode)
            .map(createdTime).toPropertyWhenPresent("createdTime", row::getCreatedTime)
            .map(updatedTime).toPropertyWhenPresent("updatedTime", row::getUpdatedTime)
            .map(remark).toPropertyWhenPresent("remark", row::getRemark)
            .map(qrCode).toPropertyWhenPresent("qrCode", row::getQrCode)
            .map(lunId).toPropertyWhenPresent("lunId", row::getLunId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysUser> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SysUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(userKey).equalTo(row::getUserKey)
                .set(mainKey).equalTo(row::getMainKey)
                .set(userNo).equalTo(row::getUserNo)
                .set(userName).equalTo(row::getUserName)
                .set(password).equalTo(row::getPassword)
                .set(userFlag).equalTo(row::getUserFlag)
                .set(userType).equalTo(row::getUserType)
                .set(status).equalTo(row::getStatus)
                .set(phoneNum).equalTo(row::getPhoneNum)
                .set(realName).equalTo(row::getRealName)
                .set(nickname).equalTo(row::getNickname)
                .set(email).equalTo(row::getEmail)
                .set(avatar).equalTo(row::getAvatar)
                .set(birthday).equalTo(row::getBirthday)
                .set(startTime).equalTo(row::getStartTime)
                .set(endTime).equalTo(row::getEndTime)
                .set(genderType).equalTo(row::getGenderType)
                .set(ownerId).equalTo(row::getOwnerId)
                .set(externalCode).equalTo(row::getExternalCode)
                .set(createdTime).equalTo(row::getCreatedTime)
                .set(updatedTime).equalTo(row::getUpdatedTime)
                .set(remark).equalTo(row::getRemark)
                .set(qrCode).equalTo(row::getQrCode)
                .set(lunId).equalTo(row::getLunId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(userKey).equalToWhenPresent(row::getUserKey)
                .set(mainKey).equalToWhenPresent(row::getMainKey)
                .set(userNo).equalToWhenPresent(row::getUserNo)
                .set(userName).equalToWhenPresent(row::getUserName)
                .set(password).equalToWhenPresent(row::getPassword)
                .set(userFlag).equalToWhenPresent(row::getUserFlag)
                .set(userType).equalToWhenPresent(row::getUserType)
                .set(status).equalToWhenPresent(row::getStatus)
                .set(phoneNum).equalToWhenPresent(row::getPhoneNum)
                .set(realName).equalToWhenPresent(row::getRealName)
                .set(nickname).equalToWhenPresent(row::getNickname)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(avatar).equalToWhenPresent(row::getAvatar)
                .set(birthday).equalToWhenPresent(row::getBirthday)
                .set(startTime).equalToWhenPresent(row::getStartTime)
                .set(endTime).equalToWhenPresent(row::getEndTime)
                .set(genderType).equalToWhenPresent(row::getGenderType)
                .set(ownerId).equalToWhenPresent(row::getOwnerId)
                .set(externalCode).equalToWhenPresent(row::getExternalCode)
                .set(createdTime).equalToWhenPresent(row::getCreatedTime)
                .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
                .set(remark).equalToWhenPresent(row::getRemark)
                .set(qrCode).equalToWhenPresent(row::getQrCode)
                .set(lunId).equalToWhenPresent(row::getLunId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SysUser row) {
        return update(c ->
            c.set(userKey).equalTo(row::getUserKey)
            .set(mainKey).equalTo(row::getMainKey)
            .set(userNo).equalTo(row::getUserNo)
            .set(userName).equalTo(row::getUserName)
            .set(password).equalTo(row::getPassword)
            .set(userFlag).equalTo(row::getUserFlag)
            .set(userType).equalTo(row::getUserType)
            .set(status).equalTo(row::getStatus)
            .set(phoneNum).equalTo(row::getPhoneNum)
            .set(realName).equalTo(row::getRealName)
            .set(nickname).equalTo(row::getNickname)
            .set(email).equalTo(row::getEmail)
            .set(avatar).equalTo(row::getAvatar)
            .set(birthday).equalTo(row::getBirthday)
            .set(startTime).equalTo(row::getStartTime)
            .set(endTime).equalTo(row::getEndTime)
            .set(genderType).equalTo(row::getGenderType)
            .set(ownerId).equalTo(row::getOwnerId)
            .set(externalCode).equalTo(row::getExternalCode)
            .set(createdTime).equalTo(row::getCreatedTime)
            .set(updatedTime).equalTo(row::getUpdatedTime)
            .set(remark).equalTo(row::getRemark)
            .set(qrCode).equalTo(row::getQrCode)
            .set(lunId).equalTo(row::getLunId)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SysUser row) {
        return update(c ->
            c.set(userKey).equalToWhenPresent(row::getUserKey)
            .set(mainKey).equalToWhenPresent(row::getMainKey)
            .set(userNo).equalToWhenPresent(row::getUserNo)
            .set(userName).equalToWhenPresent(row::getUserName)
            .set(password).equalToWhenPresent(row::getPassword)
            .set(userFlag).equalToWhenPresent(row::getUserFlag)
            .set(userType).equalToWhenPresent(row::getUserType)
            .set(status).equalToWhenPresent(row::getStatus)
            .set(phoneNum).equalToWhenPresent(row::getPhoneNum)
            .set(realName).equalToWhenPresent(row::getRealName)
            .set(nickname).equalToWhenPresent(row::getNickname)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(avatar).equalToWhenPresent(row::getAvatar)
            .set(birthday).equalToWhenPresent(row::getBirthday)
            .set(startTime).equalToWhenPresent(row::getStartTime)
            .set(endTime).equalToWhenPresent(row::getEndTime)
            .set(genderType).equalToWhenPresent(row::getGenderType)
            .set(ownerId).equalToWhenPresent(row::getOwnerId)
            .set(externalCode).equalToWhenPresent(row::getExternalCode)
            .set(createdTime).equalToWhenPresent(row::getCreatedTime)
            .set(updatedTime).equalToWhenPresent(row::getUpdatedTime)
            .set(remark).equalToWhenPresent(row::getRemark)
            .set(qrCode).equalToWhenPresent(row::getQrCode)
            .set(lunId).equalToWhenPresent(row::getLunId)
            .where(id, isEqualTo(row::getId))
        );
    }
}