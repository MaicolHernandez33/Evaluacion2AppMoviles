import retrofit2.http.GET
import com.example.restock.model.Producto

interface ApiService {
    @GET("api/productos")
    suspend fun getProductos(): List<Producto>
}