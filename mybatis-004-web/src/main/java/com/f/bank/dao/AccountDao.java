package com.f.bank.dao;

import com.f.bank.pojo.Account;

/**
 * 账户的DAO对象，负责t_act表中数据的CRUD
 * 注意：DAO对象中的任何一个方法都和业务不挂钩，没有任何的业务逻辑在里面
 * DAO中的方法只做CRUD，所以方法名大部分是：insertXxx、deleteXxx、updateXxx、selectXxx
 *
 * @author fzy
 * @date 2024/1/6 11:43
 */
public interface AccountDao {

    /**
     * 根据账号查询账户信息
     *
     * @param actno
     * @return
     */
    Account selectByActno(String actno);

    /**
     * 更新账户信息
     *
     * @param act 账户对象
     * @return 1表示更新成功，其他值表示更新失败
     */
    int updateAct(Account act);
}
