package webshop.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webshop.model.Category;
import webshop.model.CategoryDAO;
import webshop.model.Customer;
import webshop.model.CustomerDAO;
import webshop.model.Product;
import webshop.model.ProductDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping("/")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) Integer categoryId, ModelMap model) {
        getCategories(model);
        if (categoryId != null) {
            Category selectedCategory = categoryDAO.findByID(categoryId);
            model.addAttribute("selectedCategory", selectedCategory);
        }
        return "admin/categories";
    }

    @RequestMapping("/createcategory")
    public String createCategory(
            @RequestParam Integer categoryId,
            @RequestParam String name,
            ModelMap model) {
        if (categoryId > 0) {
            Category selectedCategory = categoryDAO.findByID(categoryId);
            selectedCategory.setName(name);
            categoryDAO.save(selectedCategory);
        } else {
            Category category = new Category();
            category.setName(name);
            categoryDAO.save(category);
        }
        getCategories(model);
        return "admin/categories";
    }

    @RequestMapping("/updatecategory")
    public String updateCategory(
            @RequestParam Integer categoryId,
            @RequestParam String name,
            ModelMap model) {
        Category selectedCategory = categoryDAO.findByID(categoryId);
        selectedCategory.setName(name);
        categoryDAO.update(selectedCategory);
        getCategories(model);
        model.addAttribute("selectedCategory", selectedCategory);
        return "admin/categories";
    }

    @RequestMapping("/deletecategory")
    public String deleteCategory(@RequestParam Integer categoryId, ModelMap model) {
        Category selectedCategory = categoryDAO.findByID(categoryId);
        categoryDAO.delete(selectedCategory);
        getCategories(model);
        return "admin/categories";
    }

    @RequestMapping("/products")
    public String products(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        List<Product> products = productDAO.findByPage(page - 1);
        model.addAttribute("products", products);
        model.addAttribute("totalpages", productDAO.pages());
        return "admin/products";
    }

    @RequestMapping(value = "/manageproduct", method = RequestMethod.GET)
    public String manageProduct(@RequestParam Integer productId, ModelMap model) {
        getCategories(model);
        Product product = productDAO.findByID(productId);
        model.addAttribute("product", product);
        return "admin/manageproduct";
    }

    @RequestMapping(value = "/createproduct", method = RequestMethod.POST)
    public String createProduct(
            @RequestParam Integer productId,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String publishedYear,
            @RequestParam String pagesNo,
            @RequestParam String isbn,
            @RequestParam String price,
            @RequestParam MultipartFile photo,
            @RequestParam String stock,
            @RequestParam Integer categoryId,
            HttpServletRequest request,
            ModelMap model) throws FileNotFoundException, IOException {
        getCategories(model);
        Product product = new Product();
        product.setTitle(title);
        product.setAuthor(author);
        product.setPublishedYear(publishedYear);
        product.setPagesNo(Integer.valueOf(pagesNo));
        product.setIsbn(isbn);
        product.setPrice(new BigDecimal(price));
        if (photo != null && !photo.isEmpty()) {
            String filepath = request.getServletContext().getRealPath("resources/photos");
            FileOutputStream fos = new FileOutputStream(filepath + "/" + photo.getOriginalFilename());
            product.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        product.setStock(Integer.valueOf(stock));
        Category category = categoryDAO.findByID(categoryId);
        product.setCategoryId(category);
        productDAO.save(product);
        model.addAttribute("product", product);
        return "admin/manageproduct";
    }

    @RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
    public String updateProduct(
            @RequestParam Integer productId,
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String publishedYear,
            @RequestParam String pagesNo,
            @RequestParam String isbn,
            @RequestParam String price,
            @RequestParam MultipartFile photo,
            @RequestParam String stock,
            @RequestParam Integer categoryId,
            HttpServletRequest request,
            ModelMap model) throws FileNotFoundException, IOException {
        getCategories(model);
        Product product = productDAO.findByID(productId);
        product.setTitle(title);
        product.setAuthor(author);
        product.setPublishedYear(publishedYear);
        product.setPagesNo(Integer.valueOf(pagesNo));
        product.setIsbn(isbn);
        product.setPrice(new BigDecimal(price));
        if (photo != null && !photo.isEmpty()) {
            String filepath = request.getServletContext().getRealPath("resources/photos");
            FileOutputStream fos = new FileOutputStream(filepath + "/" + photo.getOriginalFilename());
            product.setPhoto(photo.getOriginalFilename());
            fos.write(photo.getBytes());
            fos.close();
        }
        product.setStock(Integer.valueOf(stock));
        Category category = categoryDAO.findByID(categoryId);
        product.setCategoryId(category);
        productDAO.update(product);
        model.addAttribute("product", product);
        return "admin/manageproduct";
    }

    @RequestMapping("/deleteproduct")
    public String deleteProduct(@RequestParam Integer productId, ModelMap model) {
        Product selectedProduct = productDAO.findByID(productId);
        productDAO.delete(selectedProduct);
        getCategories(model);
        return "admin/manageproduct";
    }

    @RequestMapping("/customers")
    public String customers(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        List<Customer> customers = customerDAO.findByPage(page - 1);
        model.addAttribute("customers", customers);
        model.addAttribute("totalpages", customerDAO.pages());
        return "admin/customers";
    }

    @RequestMapping(value = "/managecustomer", method = RequestMethod.GET)
    public String manageCustomer(@RequestParam Integer customerId, ModelMap model) {
        Customer customer = customerDAO.findByID(customerId);
        model.addAttribute("customer", customer);
        return "admin/managecustomer";
    }

    @RequestMapping(value = "/createcustomer", method = RequestMethod.POST)
    public String createCustomer(
            @RequestParam Integer customerId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String phone,
            ModelMap model) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setNumberOfOrders(0);
        customerDAO.save(customer);
        model.addAttribute("customer", customer);
        return "admin/managecustomer";
    }

    @RequestMapping(value = "/updatecustomer", method = RequestMethod.POST)
    public String updateCustomer(
            @RequestParam Integer customerId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String email,
            @RequestParam String phone,
            ModelMap model) {
        Customer customer = customerDAO.findByID(customerId);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        customerDAO.update(customer);
        model.addAttribute("customer", customer);
        return "admin/managecustomer";
    }

    @RequestMapping("/deletecustomer")
    public String deleteCustomer(@RequestParam Integer customerId, ModelMap model) {
        Customer selectedCustomer = customerDAO.findByID(customerId);
        customerDAO.delete(selectedCustomer);
        return "admin/managecustomer";
    }

    private void getCategories(ModelMap model) {
        List<Category> categories = categoryDAO.find();
        model.addAttribute("categories", categories);
    }

}
