package com.github.fmatt.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.github.fmatt.model.Product;
import com.github.fmatt.service.ProductsService;

@Named
@ViewScoped
public class DialogUploadsView implements Serializable {

    private static final Logger logger = Logger.getLogger(DialogUploadsView.class.getName());

    @Inject
    private ProductsService productsService;

    private Product product;

    private UploadedFile uploadedFile;

    private IndexView parentView;

    public void upload(FileUploadEvent event) {
		try {
			uploadedFile = event.getFile();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "File successfully uploaded!", null));
        } catch(Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error saving product.", null));
		}
	}

    public void save() {
		try {
			if (product == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product is required", null));
				return;
			}

            if (uploadedFile == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Uploaded file is required", null));
				return;
			}

			productsService.saveProduct(product, uploadedFile.getContent());
            parentView.updateProducts();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Product successfully saved!", null));
		} catch (RuntimeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
        } catch(Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error saving product.", null));
		}
	}
	
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public IndexView getParentView() {
        return parentView;
    }

    public void setParentView(IndexView parentView) {
        this.parentView = parentView;
    }

}
