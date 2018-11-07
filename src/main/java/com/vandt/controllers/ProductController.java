package com.vandt.controllers;

import com.vandt.data.ProductData;
import com.vandt.data.CurrencyData;
import com.vandt.data.CurrencyData.CurrencyCode;
import com.vandt.model.Product;
import com.vandt.service.DataMapperService;
import com.vandt.service.ProductService;
import com.vandt.validator.ProductDataValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Currency;
import java.util.List;

/**
 * One stop shop for Product - listAll, getById, edit
 * added currency modification but should be refactored to pertinent controller
 */
@Controller
public class ProductController
{
    private static final Logger LOG = Logger.getLogger(ProductController.class);
    private static final String CURRENCYLOCALE = "currencylocale";
    private static final String GBP = "GBP";

    @Autowired
    private ProductService productService;

    @Autowired
    private DataMapperService dataMapperService;

    @Autowired
    private ProductDataValidator productDataValidator;

    @ModelAttribute("currencyCodeList")
    public CurrencyData.CurrencyCode[] currencyCodeList()
    {
        return CurrencyCode.values();
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public String listProducts(final HttpServletRequest request, final Model model)
    {
        setSessionAttribute(request, Currency.getInstance(GBP), false);

        model.addAttribute("products", productService.getProducts());  // TODO: Refactor to use ProductData
        model.addAttribute("currencyData", new CurrencyData());
        return "products";
    }

    @RequestMapping(value = "/product/list", method = RequestMethod.GET, headers = { "Accept=application/json" })
    public @ResponseBody List<ProductData> listAllProductData()
    {
        return dataMapperService.mapProductsToProductDataList(productService.getProducts());
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public String getProductById(@PathVariable("productId") final Long productId, final HttpServletRequest request, final Model model)
    {
        setSessionAttribute(request, Currency.getInstance(GBP), false);

        Product product = productService.getProductForId(productId);
        model.addAttribute("product", product);  // TODO: Refactor to use ProductData
        model.addAttribute("productid", productId);
        return "product";
    }

    @RequestMapping(value = "/product/edit/{productId}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("productId") final Long productId, HttpServletRequest request, Model model)
    {
        ProductData productData = getProductData(productId);
        model.addAttribute("productData", productData);
        return "editproduct";
    }

    public ProductData getProductData(Long productId)
    {
        return dataMapperService.mapProductToProductData(productService.getProductForId(productId));
    }

    @RequestMapping(value = "/product/edit/{productId}", method = RequestMethod.POST)
    public String updateProduct(@Valid @ModelAttribute("productData") ProductData productData, BindingResult result)
    {
        productDataValidator.validate(productData, result);

        if (result.hasErrors())
        {
            LOG.error("Something has gone wrong!!");
            return "editproduct";
        }

        // save product
        final Product product = productService.getProductForId(productData.getId());
        dataMapperService.mapProductDataToProduct(productData, product);
        productService.save(product);

        return "redirect:/product/list";
    }

    @RequestMapping(value = "/product/modifycurrency", method = RequestMethod.POST)
    public String modifySessionCurrency(@Valid @ModelAttribute("currencyData") final CurrencyData currencyData, final HttpServletRequest request, final BindingResult result)
    {
        if (result.hasErrors())
        {
            LOG.error("Something has gone wrong!!");
            return "error";
        }

        setSessionAttribute(request, Currency.getInstance(getCurrencyNameFromForm(currencyData)), true);
        return "redirect:/product/list";
    }

    public String getCurrencyNameFromForm(final CurrencyData currencyData)
    {
        return currencyData.getCurrencyCode().name();
    }

    public void setSessionAttribute(final HttpServletRequest request, final Currency currencyValue, boolean overwrite)
    {
        HttpSession session = request.getSession();
        if(overwrite || StringUtils.isEmpty(session.getAttribute(CURRENCYLOCALE)))
        {
            session.setAttribute(CURRENCYLOCALE, currencyValue);
        }
    }
}
