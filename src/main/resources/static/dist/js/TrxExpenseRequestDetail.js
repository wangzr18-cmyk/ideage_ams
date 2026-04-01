function removeFile(btn) {
    btn.closest('.file-item').remove();
}


function validateForm() {
    const fileInput = document.querySelector('input[name="receiptImage"]');

    // 判断是否选择了文件
    if (!fileInput.files || fileInput.files.length === 0) {
        alert("请先上传领収书图片！");
        return false; // 阻止提交
    }

    return true; // 允许提交
}