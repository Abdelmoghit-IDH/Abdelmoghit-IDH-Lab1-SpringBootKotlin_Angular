package inpt.sud.springbackend

import inpt.sud.springbackend.dao.CategoryRepository
import inpt.sud.springbackend.dao.ProductRepository
import inpt.sud.springbackend.data.Category
import inpt.sud.springbackend.data.Product
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import java.util.*
import java.util.function.Consumer

@SpringBootApplication
class SpringbackendApplication : CommandLineRunner {
	@Autowired
	var productRepository: ProductRepository? = null

	@Autowired
	var categoryRepository: CategoryRepository? = null

	@Autowired
	private val repositoryRestConfiguration: RepositoryRestConfiguration? = null

	@Throws(Exception::class)
	override fun run(vararg args: String) {
		repositoryRestConfiguration!!.exposeIdsFor(Product::class.java, Category::class.java)

		var cat1 = Category()
		cat1.id = null
		cat1.name = "Computers"
		cat1.description = null
		cat1.products = null

		var cat2 = Category()
		cat2.id = null
		cat2.name = "Printers"
		cat2.description = null
		cat2.products = null

		var cat3 = Category()
		cat3.id = null
		cat3.name = "Smartphones"
		cat3.description = null
		cat3.products = null

		categoryRepository!!.save(cat1)
		categoryRepository!!.save(cat2)
		categoryRepository!!.save(cat3)

		val rnd = Random()
		categoryRepository!!.findAll().forEach(Consumer { category: Category? ->
			for (i in 0..9) {
				val p = Product()
				p.name= RandomString.make(10)
				p.available=rnd.nextBoolean()
				p.currentPrice= rnd.nextInt(999).toDouble()
				p.promotion=rnd.nextBoolean()
				p.selected=rnd.nextBoolean()
				p.category=category
				p.photoName="unknown.png"
				productRepository!!.save(p)
			}
		})
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(SpringbackendApplication::class.java, *args)
		}
	}
}