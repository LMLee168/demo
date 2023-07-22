package com.demo.common.service.impl;

import com.demo.common.mapper.SavvyBuyMapper;
import com.demo.common.model.po.UserSearchGoodsPO;
import com.demo.common.service.ISavvyBuyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class SavvyBuyServiceImpl implements ISavvyBuyService {

    @Resource
    private SavvyBuyMapper savvyBuyMapper;

    @Override
    public int addSelective(UserSearchGoodsPO po) {
        po.setCreateTime(LocalDateTime.now());
        return savvyBuyMapper.insertSelective(po);
    }
}
