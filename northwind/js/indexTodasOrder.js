const URL = "http://localhost:8080/Rest_AndresVillani_DanielGil/webapi/ordenes";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  const queryStr = window.location.search.substring(1);
  const parametro = queryStr.split("=");
  idpedidos = parametro[1];
  const peticionHTTP = fetch(URL);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((ordenes) => {
      let tblBody = document.getElementById("id_tblPedidos");
      for (const orders of ordenes) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = orders.id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.ship_name;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.ship_address;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.order_date;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.paid_date;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.payment_type;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = orders.ship_zip_postal_code;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML =
          `<button class="btn btn-link" onclick="editaPedido(${orders.id})"><i class="bi-pencil"></i></button>` +
          `<button style="color:red;" class="btn btn-link"  onclick="borrarPedido(${orders.id})"><i class="bi-x-circle"></i></button>`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }

    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de los Pedidos!<br>" + error, false, "error");
    });
}

function editaPedido(idpedidos) {
  window.location.href = `editarPedido.html?idpedidos=${idpedidos}`;
}

function addPedido() {
  window.location.href = "editarPedido.html";
}

function indexDetalles(idpedidos) {
  window.location.href = `indexDetalles.html?idpedidos=${idpedidos}`;
}

function borrarPedido(idpedidos) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el pedido ${idpedidos}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarPedidoAPI(idpedidos);
  });
}

function borrarPedidoAPI(idpedidos) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(URL + "/" + idpedidos, opciones)
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
      muestraMsg(`¡Pedido ${idpedidos} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "Pedido NO borrado",
        "¿Es posible que este pedido lo tenga algun cliente? 🤔<br>" + error,
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

