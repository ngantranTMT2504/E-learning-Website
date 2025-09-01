const timeAgo = (dateStr) => {
    if (!dateStr) return "";

    const now = new Date();
    const past = new Date(dateStr); 

    if (isNaN(past.getTime())) return "";

    let diff = Math.floor((now - past) / 1000);
    if (diff < 0) diff = 0;

    const MINUTE = 60;
    const HOUR = 3600;
    const DAY = 86400;
    const MONTH = DAY * 30;
    const YEAR = DAY * 365;

    if (diff < MINUTE) return "Vừa xong";           // dưới 1 phút
    if (diff < HOUR) return `${Math.floor(diff / MINUTE)} phút trước`;
    if (diff < DAY) return `${Math.floor(diff / HOUR)} giờ trước`;
    if (diff < MONTH) return `${Math.floor(diff / DAY)} ngày trước`;
    if (diff < YEAR) return `${Math.floor(diff / MONTH)} tháng trước`;
    return `${Math.floor(diff / YEAR)} năm trước`;
};

export default timeAgo;
