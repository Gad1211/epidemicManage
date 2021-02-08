package com.gad.epidemicmanage.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gad.epidemicmanage.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
