const formatPrice = (value) => {
  const number = Number(value);
  if (isNaN(number)) return '0₫';
  return new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
  }).format(number);
};

export default formatPrice;