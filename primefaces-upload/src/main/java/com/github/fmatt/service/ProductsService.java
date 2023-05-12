package com.github.fmatt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.fmatt.model.Product;

@Stateless
public class ProductsService {

    private final static Logger logger = Logger.getLogger(ProductsService.class.getName());

    private final static String UPLOAD_ROOT = "/tmp/uploads";

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public void saveProduct(Product product, byte[] fileContent) {
        if (product == null)
			throw new RuntimeException("Product is mandatory.");
		
		if (product.getName() == null || product.getName().isBlank())
			throw new RuntimeException("Product name is mandatory.");
		
		if (fileContent == null || fileContent.length == 0)
			throw new RuntimeException("Content is mandatory.");
		
		OutputStream outputStream = null;
		try {
			entityManager.persist(product);
			
			String directoryStr = String.format("%s/%d", UPLOAD_ROOT, product.getId());
			File directory = new File(directoryStr);
			if (!directory.exists())
				directory.mkdirs();
			
			String path = String.format("%s/%d.pdf", directoryStr, product.getId());
			File file = new File(path);
			outputStream = new FileOutputStream(file);
			outputStream.write(fileContent);
			outputStream.close();
			
			product.setPath(path);
			entityManager.merge(product);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException("Error saving attachment.");
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// Pass
				}
			}
		}
    }

}
