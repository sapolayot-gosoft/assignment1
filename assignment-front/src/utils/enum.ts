export default {
    getOrderStatus: (orderStatus: string): string => {
        if (orderStatus === "waiting") {
            console.log("ok")
            return "กำลังดำเนินการ"
        } else if (orderStatus === "success") {
            return "สำเร็จ"
        } else if (orderStatus === "cancel_by_buyer") {
            return "ยกเลิกโดยผู้ซื้อ"
        } else if (orderStatus === "cancel_by_seller") {
            return "ยกเลิกโดยผู้ขาย"
        } else {
            return ""
        }
        // return ["กำลังดำเนินการ","สำเร็จ","ยกเลิกโดยผู้ซื้อ","ยกเลิกโดยผู้ขาย"][orderStatus]
    }
}