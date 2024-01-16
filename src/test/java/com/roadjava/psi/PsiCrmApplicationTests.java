package com.roadjava.psi;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.mapper.PurchaseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PsiCrmApplicationTests {

    @Resource
    private PurchaseMapper purchaseMapper;
    @Test
    void contextLoads() {
        LambdaQueryWrapper<PurchaseDO> qw = new LambdaQueryWrapper<>();
        List<PurchaseDO> purchaseDOS = purchaseMapper.selectList(qw);
        System.out.println(purchaseDOS);
    }

}
