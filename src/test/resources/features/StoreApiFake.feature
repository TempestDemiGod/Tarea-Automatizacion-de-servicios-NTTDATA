@AllProducts
Feature: API from FakeStore

  Como usuario
  Quiero obtener la lista de productos
  Para poder visualizar los detalles de todos los productos

  @CP01_Product @GetAllProducts
  Scenario: Obtener todos los productos exitosamente
    Given el actor establece el endpoint de products
    When el actor realiza la solicitud GET
    Then el codigo de respuesta debe ser 200

  @CP02_Product @PostProduct
 Scenario Outline: Crear un nuevo producto
    Given el actor establece el endpoint de products
    When el actor crea un product con "<title>" "<price>"
    Then el codigo de respuesta debe ser 201

    Examples:
      | title   | price  |
      | tempest | 321.20 |
      | Rimuru  | 600.00 |

  @CP03_Product @PutProduct
 Scenario Outline: Actualizar los datos del Producto exitosamente
    Given el actor establece el endpoint de products
    When el actor crea un product con "tempest" 421.20
    When el actor actualiza el producto con los datos "<title>" "<price>"
    Then el codigo de respuesta debe ser 200

    Examples:
      | title               | price  |
      | tempest Actualizado | 421.20 |

  @CP01_Product @DeleteProducts
  Scenario: Eliminar un producto exitosamente
    Given el actor establece el endpoint de products
    When el actor realiza la solicitud Delete
    Then el codigo de respuesta debe ser 200
