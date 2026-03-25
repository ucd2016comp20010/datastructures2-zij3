# 19-Entry Hash Table

`AbstractHashMap` uses the MAD formula

```text
h(k) = ((k * scale + shift) mod prime) mod capacity
```

with:

- `prime = 109345121`
- `capacity = 19`
- `scale` and `shift` chosen at random

Because `scale` and `shift` are random in the code, there is no single unique 19-slot table unless those two values are specified.

If we use the simplest explicit MAD instance:

```text
scale = 1
shift = 0
```

then the hash function becomes:

```text
h(k) = k mod 19
```

For the keys `12, 44, 13, 88, 23, 94, 11, 39, 20, 16, 5`, the buckets are:

- `12 mod 19 = 12`
- `44 mod 19 = 6`
- `13 mod 19 = 13`
- `88 mod 19 = 12`
- `23 mod 19 = 4`
- `94 mod 19 = 18`
- `11 mod 19 = 11`
- `39 mod 19 = 1`
- `20 mod 19 = 1`
- `16 mod 19 = 16`
- `5 mod 19 = 5`

Using separate chaining, the 19-entry hash table is:

```text
0
1   39 -> 20
2
3
4   23
5   5
6   44
7
8
9
10
11  11
12  12 -> 88
13  13
14
15
16  16
17
18  94
```

If your lecturer expects specific MAD constants for `scale` and `shift`, send them and the table can be recomputed exactly.
