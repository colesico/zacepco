function ImageUploader(){


	this.width = 240;
	this.height = 240;

	this.onImageFile = function(file){};
	
	this.inputFileChange = function(e) {

          self = this;

		  files = e.target.files || e.dataTransfer.files;
		  if (!files.length) {
			alert('Файл не выбран');
			return;
		  }

		  if (typeof (FileReader) === 'undefined') {
			alert('Ваш браузер не поддерживает загрузку файлов');
			return;
		  }

		  reader = new FileReader();
		  reader.onload = function(e) {
			if (!(files[0].name.toLowerCase().endsWith('.jpg') || files[0].name.toLowerCase().endsWith('.png'))) {
			  alert('Можно загружать только *.jpg или *.png файлы');
			  return;
			}
			
			file = {
				name: files[0].name,
				size: files[0].size,
				type: files[0].type,
				content: null,
				src: null
			};
			
			img = new Image();
			img.onload = function() {
			  // output image
			  canvas = minimizeImage(img, self.width, self.height);
			  dataUrl = canvas.toDataURL();
			  file.src = dataUrl;
			  file.content = dataUrl.split(',')[1];
			  self.onImageFile(file);
			};
			img.src = e.target.result;
			
		  };
		  
		  reader.readAsDataURL(files[0]);
	}

}

function minimizeImage(img, dstMaxWidth, dstMmxHeight) {
	srcWidth = img.width;
	srcHeight = img.height;

	ratioWidth = dstMaxWidth / srcWidth;
	ratioHeight = dstMmxHeight / srcHeight;

	let ratio = 0.0;
	if (ratioWidth < ratioHeight) {
		ratio = ratioWidth;
	} else {
		ratio = ratioHeight;
	}

	if (ratio > 1.0) {
		ratio = 1.0;
	}

	dstWidth = Math.round(srcWidth * ratio);
	dstHeight = Math.round(srcHeight * ratio);

	canvas = document.createElement('canvas');
	canvas.width = dstWidth;
	canvas.height = dstHeight;

	ctx = canvas.getContext('2d');
	ctx.drawImage(img, 0, 0, srcWidth, srcHeight, 0, 0, dstWidth, dstHeight);
	return canvas;
}