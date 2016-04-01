gcc ./src/main.c -o ./bin/main.out

./bin/main.out < ./testcases/t0.txt > ./testcases/o0.txt
diff ./testcases/o0.txt ./testcases/e0.txt