class Sum {
static long sum (long n, long result) {
   if (n == 0) {
return result;
} else {
return sum(n - 1, n + result);
}
}
}
