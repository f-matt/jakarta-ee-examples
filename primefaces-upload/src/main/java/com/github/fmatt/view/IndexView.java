package com.github.fmatt.view;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.github.fmatt.model.Product;
import com.github.fmatt.service.ProductsService;

@Named
@ViewScoped
public class IndexView implements Serializable {
	
	private static final Logger logger = Logger.getLogger(IndexView.class.getName());

	@Inject
	private DialogUploadsView dialogUploadsView;

	@Inject
	private ProductsService productsService;

	private List<Product> products;

	@PostConstruct
	public void init() {
		updateProducts();
	}

	public void updateProducts() {
		try {
			products = productsService.findAll();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}


	public void openUploadDialog() {
		Product product = new Product();

		dialogUploadsView.setProduct(product);
		dialogUploadsView.setParentView(this);

		PrimeFaces.current().executeScript("PF('dlgUpload').show()");
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
