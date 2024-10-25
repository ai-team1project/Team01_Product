package org.koreait.product.entities;

import org.koreait.global.BeanContainer;
import org.koreait.product.services.ProductInfoService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class RepositoryImp implements ProductRepository{

    private final ProductInfoService infoService;

    public RepositoryImp() {
        infoService = BeanContainer.getBean(ProductInfoService.class);
    }

    @Override
    public Product findById(long id) {
        return infoService.get(id);
    }

    @Override
    public void delete(long id) {
        Map<Long, Product> data = infoService.load();
        data.remove(id);

        try (FileOutputStream fos = new FileOutputStream("products.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
        } catch (IOException e) {}
    }

    @Override
    public List<Product> findAll() {
        return infoService.getList();
    }
}
