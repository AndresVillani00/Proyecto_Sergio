const URL = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/ordenes/editar";
const URL2 = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/ordenes";
const URL3 = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/DetallesPedidos/pedido";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  if (window.location.search != "") {
    const queryStr = window.location.search.substring(1);
    const parametro = queryStr.split("=");
    idpedidos = parametro[1];

    rellenaPedido(idpedidos);
    console.log("PedidoID:   ", idpedidos)
  } else {
    document.getElementById("idId").value = "Nuevo Pedido";
    document.getElementById("idSalvar").disabled = false;
  }

  // Usa el boton de cancelar para volver atrás
  document.getElementById("idCancel").addEventListener("click", (evt) => {
    evt.preventDefault();
    volver();
  });

  document.getElementById("idFormPedido").addEventListener("submit", salvarPedido);

  const queryStr = window.location.search.substring(1);
  const parametro = queryStr.split("=");
  iddetalles = parametro[1];  
  console.log("Estoy en orden ", parametro[1], " ", iddetalles)
  const peticionHTTP3 = fetch(URL3 + "/" + iddetalles);

  peticionHTTP3
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((detalles) => {
      let tblBody = document.getElementById("id_tblDetalles");
      for (const details of detalles) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = details.order_id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.product_id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = `Nada`;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = `Nada`;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.quantity;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.unit_price + ` €`;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = details.discount + ` %`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }

  
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de los detalles!<br>" + error, false, "error");
    });

}

function editaDetalles(iddetalles) {
  window.location.href = `editarDetalles.html?iddetalles=${iddetalles}`;
}

function rellenaPedido(idpedidos) {
  const peticionHTTP = fetch(URL + "/" + idpedidos);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((Pedido) => {
      let inputs = document.getElementsByTagName("input");
      for (let input of inputs) {
        input.value = Pedido[input.name] ?? "";
      }
      document.getElementById("idSalvar").disabled = false;
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "No he podido recupera este Pedido " + error, false);
    });
}

function salvarPedido(evt) {
  console.log("Pedido", idpedidos)
  const peticionHTTP2 = fetch(URL2);

  evt.preventDefault();

  // Creo un array con todo los datos formulario
  let Pedido = {};

  // Relleno un array cliente con todos los campos del formulario
  let inputs = document.getElementsByTagName("input");
  for (let input of inputs) {
    Pedido[input.name] = input.value;
  }

  if (Pedido.id == "Nuevo Pedido") { // Añadimos cliente
    delete Pedido.id;
    opciones = {
      method: "POST", // Añadimos un registro a la BBDD
      body: JSON.stringify(Pedido), // Paso el array cliente a un objeto que luego puedo jsonear
      headers: {
        "Content-Type": "application/json",
      },
    };
  } else {  // Modificamos
    opciones = {
      method: "PUT", // Modificamos la BBDD
      body: JSON.stringify(Pedido), // Paso el array cliente a un objeto que luego puedo jsonear
      headers: {
        "Content-Type": "application/json",
      },
    };
  }
  //creo que esta mal
  console.log("Antes Fetch")
  fetch(URL2, opciones)
  peticionHTTP2
    .then((respuesta) => {
      console.log("Estoy en fetch")
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Fallo al actualizar: " + respuesta);
    })
    .then((respuesta) => {
      muestraMsg("Datos Actualizados", "Todo parace haber ido bien 🎉", false, "success");
    })
    .catch((error) => {
      muestraMsg("Oops..", "No he podido actulizar la Base de Datos 🥺 " + error, false, "error");
    });

  return false;
}

function volver() {
  window.history.back();
}

function borrarDetalle(iddetalles) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el detalles ${iddetalles}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarDetalleAPI(iddetalles);
  });
}

function borrarDetalleAPI(iddetalles) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(URL3 + "/" + iddetalles, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else 
      {
        throw new Error(`Fallo al borrar, el servidor responde con ${respuesta.status}-${respuesta.statusText}`);
      }
        
    })
    .then((respuesta) => {
      modalWait.hide();
      muestraMsg(`¡detalles ${iddetalles} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "detalles NO borrado",
        "¿Es posible que este Detalle lo tenga algun cliente? 🤔<br>" + error,
        false,
        "error"
      );
    });
}

/**
 * Muestra un mensaje en el modal
 */
function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "Close") {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo = "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " + titulo;
      }
      break;
    case "question":
      {
        titulo = "<i style='color:blue' class='bi bi-question-circle-fill'></i> " + titulo;
      }
      break;
    default:
      {
        titulo = "<i style='color:green' class='bi bi-check-circle-fill'></i> " + titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton ? "block" : "none";

  myModal.show();
}
