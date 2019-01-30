// var i = -1;
// function addToList(){
//     var text = document.getElementById("vars").value,
//         listNode = document.getElementById("list"),
//         liNode = document.createElement("LI"),
//         txtNode = document.createTextNode(text);
//
//     i++
//     liNode.appendChild(txtNode);
//     liNode.setAttribute("id", "liList")
//     liNode.setAttribute("th:text", "${value["+i+"]}");
//     listNode.appendChild(liNode);
// }

let i = 1;

function addInput(){
    let inputDiv = document.getElementById("newInputDiv"),
        inputNode = document.createElement("input");

    inputNode.setAttribute("name", "list[" + i + "]");
    inputNode.setAttribute("type", "text");
    inputNode.setAttribute("id", "vars");
    inputDiv.appendChild(inputNode);
    i++;
}