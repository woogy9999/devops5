package com.sist.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.MemberVO;

@Mapper
public interface MemberMapper {

    @Select("SELECT id, name FROM member WHERE id = #{id} AND pwd = #{pwd} ")
    public MemberVO memberLogin(@Param("id") String id, @Param("pwd") String pwd);
    

    @Select("SELECT COUNT(*) FROM member WHERE id = #{id} ")
    public int memberIdCount(@Param("id") String id);

    @Select("SELECT id, pwd, name FROM member WHERE id = #{id} ")
    public MemberVO memberInfoData(@Param("id") String id);
}
