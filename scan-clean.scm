(define (scan-clean file-in file-out)
  (let* ((image (car (gimp-file-load RUN-NONINTERACTIVE file-in file-in)))
         (drawable (car (gimp-image-get-active-layer image))))
    (gimp-threshold drawable 191 255)
    (plug-in-gauss-iir2 RUN-NONINTERACTIVE image drawable 5.0 5.0)
    (gimp-levels drawable HISTOGRAM-VALUE 111 143 1.0 0 255)
    (plug-in-colortoalpha RUN-NONINTERACTIVE image drawable '(255 255 255))
    (gimp-file-save RUN-NONINTERACTIVE image drawable file-out file-out)))
