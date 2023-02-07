Feature: Realizar la simulacion de una compra en Freeze PreUnic
  Scenario: Agregar productos a carro simulando compra
    Given El usuario abre el navegador e ingresa a la url de freeze Preunic
    When El usuario ingresa en el buscador un producto
    When El usuario selecciona el producto a comprar de la busqueda y lo deja en carro
    When El usuario ingresa SKU de producto en buscador
    When El usuario selecciona el segundo producto y lo deja en carro
    Then Comprobar que productos seleccionados en Carro