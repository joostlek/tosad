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

function addInput(){
    var inputDiv = document.getElementById("newInputDiv"),
        inputNode = document.createElement("input");

    inputNode.setAttribute("th:field", "*{properties['__${attribute.key}__']}");
    inputNode.setAttribute("type", "text");
    inputDiv.appendChild(inputNode);
}