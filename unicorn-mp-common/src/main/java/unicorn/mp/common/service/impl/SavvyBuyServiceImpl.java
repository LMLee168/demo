package unicorn.mp.common.service.impl;

import unicorn.mp.common.mapper.SavvyBuyMapper;
import unicorn.mp.common.model.po.UserSearchGoodsPO;
import unicorn.mp.common.service.ISavvyBuyService;
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
