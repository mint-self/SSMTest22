package com.xm.mall.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xm.mall.MallApplicationTests;
import com.xm.mall.enums.ResponseEnum;
import com.xm.mall.form.CartAddForm;
import com.xm.mall.form.CartUpdateForm;
import com.xm.mall.vo.CartVo;
import com.xm.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mintFM
 * @create 2022-01-27 18:08
 */
@Slf4j
public class ICartServiceTest extends MallApplicationTests {

    @Autowired
    private ICartService cartService;

    //使用JSon的格式化，使得打印出来比较美观
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //把这两个提取出来，是为了可以一次性运行整个test
    private Integer uid = 1;

    private Integer productId = 26;

    //让每次测试前先运行新增购物车
    @Before
    public void add() {
        log.info("【新增购物车：】");
        CartAddForm form = new CartAddForm();
        form.setProductId(productId);
        form.setSelected(true);
        ResponseVo<CartVo> responseVo = cartService.add(uid,form);
        log.info("list = {}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo<CartVo> responseVo = cartService.list(uid);
        log.info("list = {}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void update() {
        CartUpdateForm form = new CartUpdateForm();
        form.setQuantity(5);
        form.setSelected(false);
        ResponseVo<CartVo> responseVo = cartService.update(uid,productId,form);
        log.info("result = {}",gson.toJson(responseVo));
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    //最后再进行删除购物车的操作
    @After
    public void delete() {
        log.info("【删除购物车：】");
        ResponseVo<CartVo> responseVo = cartService.delete(uid,productId);
        log.info("result = {}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void selectAll() {
        ResponseVo<CartVo> responseVo = cartService.selectAll(uid);
        log.info("result = {}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void unSelectAll() {
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(uid);
        log.info("result = {}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());
    }

    @Test
    public void sum() {
        ResponseVo<Integer> responseVo = cartService.sum(uid);
        log.info("result = {}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), responseVo.getStatus());

    }


}