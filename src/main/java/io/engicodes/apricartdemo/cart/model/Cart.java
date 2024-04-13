package io.engicodes.apricartdemo.cart.model;

import io.engicodes.apricartdemo.product.model.Product;
import io.engicodes.apricartdemo.user.User;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_seq"
    )
    @SequenceGenerator(
            name = "cart_seq",
            allocationSize = 1
    )
    private Integer cartId;
    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;
    @OneToMany
    @JoinColumn(name = "productId")
    private List<Product> productId;
    @OneToOne
    @JoinColumn(name = "cartId")
    private Warehouse warehouseId;
    private Integer quantity;
    private Double total;
    public void addProductToCart(Product product){
        productId.add(product);
    }
    public void deleteProductFromCart(Product product){
        productId.remove(product);
    }
    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
        quantity--;
    }

}
