package webshop.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop.model.Category;
import webshop.model.CategoryDAO;
import webshop.model.Customer;
import webshop.model.CustomerDAO;
import webshop.model.OrderDetails;
import webshop.model.OrderDetailsDAO;
import webshop.model.Orders;
import webshop.model.OrdersDAO;
import webshop.model.Product;
import webshop.model.ProductDAO;

@Controller
public class SiteController {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrdersDAO ordersDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping("/confirm")
    public String confirmOrder(HttpServletRequest request, @RequestParam(required = false) String customerData, ModelMap model) {
        getCategories(model);
        if (customerData == null || customerData.equals("")) {
            return "confirm";
        } else {
            Orders order = new Orders();
            order.setOrderTime(new Date(new java.util.Date().getTime()));
            int customerId = Integer.valueOf(customerData);
            Customer customer = customerDAO.findByID(customerId);
            order.setCustomerId(customer);
            ordersDAO.save(order);
            OrderDetails orderDetails = new OrderDetails();
            HttpSession session = request.getSession();
            HashMap<Integer, Product> sessionProducts = (HashMap<Integer, Product>) session.getAttribute("cart");
            for (Map.Entry<Integer, Product> p : sessionProducts.entrySet()) {
                orderDetails.setProductId(p.getValue().getProductId());
                orderDetails.setQuantity(p.getValue().getQuantity());
                orderDetails.setOrderId(order);
                orderDetailsDAO.save(orderDetails);
                p.getValue().setStock(p.getValue().getStock() - p.getValue().getQuantity());
                productDAO.update(p.getValue());
            }
            int numberOfOrders = customer.getNumberOfOrders();
            numberOfOrders++;
            customer.setNumberOfOrders(numberOfOrders);
            customerDAO.update(customer);
            session.removeAttribute("cart");
            return "confirmed";
        }
    }

    @RequestMapping("/remove")
    public String remove(HttpServletRequest request, @RequestParam(required = true) int productId, ModelMap model) {
        getCategories(model);
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") != null) {
            HashMap<Integer, Product> products = (HashMap<Integer, Product>) session.getAttribute("cart");
            if (products.containsKey(productId)) {
                products.remove(productId);
            }
        }
        return "remove";
    }

    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, ModelMap model) {
        getCategories(model);
        List<Product> products = new ArrayList<Product>();
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") != null) {
            HashMap<Integer, Product> sessionProducts = (HashMap<Integer, Product>) session.getAttribute("cart");
            for (Map.Entry<Integer, Product> p : sessionProducts.entrySet()) {
                products.add(p.getValue());
            }
        }
        model.addAttribute("products", products);
        return "cart";
    }

    @RequestMapping("/addtocart")
    public String addToCart(HttpServletRequest request, @RequestParam(required = true) Integer productId, @RequestParam(required = true) Integer quantity, ModelMap model) {
        getCategories(model);
        Product product = productDAO.findByID(productId);
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);

        HttpSession session = request.getSession();
        HashMap<Integer, Product> cart;
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new HashMap<Integer, Product>());
        }
        cart = (HashMap<Integer, Product>) session.getAttribute("cart");
        product.quantity = quantity;
        if (!cart.containsKey(productId)) {
            cart.put(productId, product);
        } else {
            Product productFromCart = cart.get(productId);
            productFromCart.quantity += quantity;
        }
        return "addedtocart";
    }

    @RequestMapping("/tocart/{productId}")
    public String toCart(@PathVariable int productId, ModelMap model) {
        getCategories(model);
        Product product = productDAO.findByID(productId);
        model.addAttribute("product", product);
        return "tocart";
    }

    @RequestMapping("/")
    public String index(ModelMap model) {
        getCategories(model);
        List<Product> products = productDAO.find();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/{categoryId}")
    public String byCategory(@PathVariable int categoryId, ModelMap model) {
        getCategories(model);
        List<Product> products = productDAO.findByCategory(categoryId);
        model.addAttribute("products", products);
        return "index";
    }

    private void getCategories(ModelMap model) {
        List<Category> categories = categoryDAO.find();
        model.addAttribute("categories", categories);
    }

}
