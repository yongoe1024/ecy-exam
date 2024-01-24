document.oncontextmenu = function (event) { return false }//屏蔽鼠标右键 
// document.onselectstart = function (event) { return false }//屏蔽鼠标选择，富文本会坏
window.onhelp = function () { return false } //屏蔽F1帮助 
document.onmousedown = function (event) {
  if (event.button == 1) { return false }	//屏蔽鼠标中键
}
document.onkeydown = function (event) {
  if (event.code == 'Escape') { return false }  //屏蔽ESC
  if (event.code == 'F3') { return false }  //屏蔽F3
  if (event.code == 'F5') { return false }  //屏蔽F5
  if (event.code == 'F11') { return false }  //屏蔽F11
  if (event.code == 'F12') { return false }  //屏蔽F12
  if (event.metaKey || event.ctrlKey) {
    if (event.key == 'c') return false
    if (event.key == 'v') return false
    if (event.key == 'f') return false
    if (event.key == 'w') return false
    if (event.key == 'e') return false
    if (event.key == 'h') return false
    if (event.key == 'i') return false
    if (event.key == 'o') return false
    if (event.key == 'l') return false
    if (event.key == 'p') return false
    if (event.key == 'b') return false
    if (event.key == 'n') return false
    if (event.key == 'KeyA') return false
    if (event.key == 'KeyA') return false
    if (event.key == 'KeyA') return false
  }
  if (event.shiftKey && event.code == 'F10') { return false }  //屏蔽 shift+F10 
}

var de = () => {
  setInterval(function () {
    debugger
  }, 1000)
}
// de()
window.onbeforeunload = function () {
  return "确定离开该页面吗？"
}