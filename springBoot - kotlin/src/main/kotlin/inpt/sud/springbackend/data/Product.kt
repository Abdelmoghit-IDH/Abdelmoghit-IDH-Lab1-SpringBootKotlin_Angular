package inpt.sud.springbackend.data

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import lombok.ToString
import java.io.Serializable
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Product: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name: String? = null
    var description: String? = null
    var currentPrice = 0.0
    var promotion = false
    var selected = false
    var available = false
    var photoName: String? = null

    @ManyToOne
    var category: Category? = null
}