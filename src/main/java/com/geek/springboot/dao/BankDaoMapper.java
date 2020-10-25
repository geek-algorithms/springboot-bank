package com.geek.springboot.dao;

import com.geek.springboot.pojo.LogBean;
import com.geek.springboot.pojo.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BankDaoMapper {

    @Select("select * from t_user where username=#{username} and password=#{password} and identity=#{identity}")
    public UserBean login(UserBean user);
    /**
     * 查找帐户 存在返回 true
     * @return
     */
    @Select("select * from t_user where username=#{username}")
    public UserBean findUser(String username);

    /**
     * 注册
     */
    @Insert("insert into t_user values(default,#{username},#{password},1,0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertUser(UserBean user);

    /**
     * 存款
     * @param user
     * @param money
     * @return
     */
    @Update("update t_user set money=money+#{money} where id=#{user.id}")
    public void deposit(UserBean user,double money);

    /**
     * 取款
     * @param user
     * @param money
     * @return
     */
    @Update("update t_user set money=money-#{money} where id=#{user.id}")
    public void withdraws(UserBean user,double money);

    /**
     * 转入
     * @param username 目标帐户
     * @param money 金额
     */
    @Update("update t_user set money=money+#{money} where username=#{username}")
    public void transferIn(String username, double money);

    /**
     * 转出
     * @param username
     * @param money
     * @return
     */
    @Update("update t_user set money=money-#{money} where username=#{username}")
    public void transferOut(String username, double money);
    /**
     * 添加日志
     */
    @Insert("insert into t_log values(default,#{log_type},#{log_amount},#{log_time},#{userid.id})")
    @Options(useGeneratedKeys = true, keyProperty = "log_id")
    public void addLog(LogBean log);

    /**
     * 查询帐户明细，操作记录。
     */
    @Select("select * from t_log where userid=#{id}")
    public List<LogBean> findOpration(UserBean user);

    /**
     * 管理员冻结用户
     * @param name
     */
    @Update("update t_user set identity=0 where username=#{name}")
    public void freezeAccount(String name);

    /**
     * 解除冻结
     * @param name
     * @return
     */
    @Update("update t_user set identity=1 where username=#{name}")
    public void refreshAccount(String name);
}
