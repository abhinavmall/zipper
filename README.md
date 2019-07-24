# zipper
Zipper service

#### Request Zip
curl --request POST \
  --url http://localhost:9000/compress \
  --header 'content-type: application/json' \
  --data '{
	"inputDir": "D:\\filereader",
	"outputDir": "D:\\filereaderzip\\abc.zip",
	"maxSize":4
}'

#### Request unzip 
curl --request POST \
  --url http://localhost:9000/decompress \
  --header 'content-type: application/json' \
  --data '{
	"inputDir": "D:\\filereaderzip\\abc.zip",
	"outputDir": "D:\\filereaderzip\\"
}'