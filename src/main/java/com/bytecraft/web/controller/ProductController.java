package com.bytecraft.web.controller;

import com.bytecraft.domain.product.Product;
import com.bytecraft.service.ProductService;
import com.bytecraft.web.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.Collection;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;

@Component
@Path("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GET
    @Path("/products/{productId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findOne(@PathParam("productId") Long id) {
        return Response.accepted(productService.findOne(id)).build();
    }

    @GET
    @Path("/products")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findAll() {
        GenericEntity<Collection<Product>> products =
                new GenericEntity<Collection<Product>>(newArrayList(productService.findAll())){};

        return Response.accepted(products).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/products")
    public Response create(ProductForm form) {
        return Response.status(201).entity(productService.save(form)).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/products/{productId}")
    public Response update(@PathParam(value = "productId") Long productId, ProductForm form) throws URISyntaxException {
        Product product = productService.findOne(productId);
        if (product == null) {
            return Response.status(409).build();
        }
        productService.update(product, form);
        return Response.noContent().build();
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/products/{productId}")
    public Response delete(@PathParam("productId") Long productId) {
        productService.delete(productId);
        return Response.noContent().build();
    }

    @GET
    @Path("/products/fields")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fetchBasedOnPartialRequest(@DefaultValue("false") @QueryParam("fetchChildProducts") boolean fetchChildProducts,
                                               @DefaultValue("false") @QueryParam("fetchImages") boolean fetchImages) {

        return Response.accepted(productService.fetchBasedOnPartialRequest(fetchChildProducts, fetchImages)).build();
    }

    @GET
    @Path("/products/{productId}/fields")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fetchProductBasedOnPartialRequest(@PathParam("productId") Long id,
                                                      @DefaultValue("false") @QueryParam("fetchChildProducts") boolean fetchChildProducts,
                                                      @DefaultValue("false") @QueryParam("fetchImages") boolean fetchImages) {

        return Response.accepted(productService.fetchProductBasedOnPartialRequest(id, fetchChildProducts, fetchImages)).build();
    }

    @GET
    @Path("/products/{productId}/images")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fetchAllImagesByProduct(@PathParam("productId") Long productId) {
        return Response.accepted(productService.fetchImagesByProduct(productId)).build();
    }

    @GET
    @Path("/products/{productId}/children")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response fetchAllChildrenByProduct(@PathParam("productId") Long productId) {
        return Response.accepted(productService.fetchChildrenByProduct(productId)).build();
    }
}
