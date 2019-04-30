
import numpy as np
from scipy import signal
import matplotlib.pyplot as plt
import cv2

def Down2D( x ):

    n, m =  x.shape

    xDown = np.zeros( ( np.int( n / 2 ) , np.int( m / 2 ) ) )

    xDown = x[ 1 : n : 2 , 1 : m : 2 ]

    return xDown

def Up2D( x ):

    n, m = x.shape

    xUp = np.zeros( ( 2 * n  , 2 * m ) )

    xUp[0 : 2 * n : 2 , 0 : 2 * n : 2 ] = x

    return xUp


def HaarDecom2D( x ):

    Dl = 1/4*np.ones( ( 2 , 2 ) ).reshape( [ 2 , 2 ] )

    Dh1 = 1/4*np.array( [ - 1 , 1 , - 1 , 1 ] ).reshape( [ 2 , 2 ] )

    Dh2 = 1/4*np.array( [ -1 , -1 , 1 , 1 ] ).reshape( [ 2 , 2 ] )

    Dh3 = 1/4*np.array( [ 1 , -1 , -1 , 1 ] ).reshape( [ 2 , 2 ] )

    xlowD = Down2D( signal.convolve2d( x , Dl , boundary='fill' , mode='same' ) )

    xhighD1 = Down2D( signal.convolve2d( x , Dh1 , boundary='fill' , mode='same' ) )

    xhighD2 = Down2D( signal.convolve2d( x , Dh2 , boundary='fill' , mode='same' ) )

    xhighD3 = Down2D( signal.convolve2d( x , Dh3 , boundary='fill' , mode='same' ) )

    xDecom2D = np.vstack( [ np.hstack( [xlowD, xhighD1 ] ) , np.hstack( [xhighD2, xhighD3 ] ) ] )

    return xDecom2D


def HaarRecon2D( x ):

    n , m = x.shape

    x1 = x[ 0 : np.int( n / 2 ) ,  0 : np.int( m / 2 ) ]

    x2 = x[ 0 : np.int( n / 2 )  , np.int( m / 2 ) : ]

    x3 = x[ np.int( n / 2 ) : n ,  0 : np.int( m / 2 ) ]

    x4 = x[ np.int( n / 2 ) : n , np.int( m / 2 ) : ]

    Rl1 = np.ones( ( 2 , 2 ) ).reshape( [ 2 , 2 ] )

    Rh1 = np.array( [ 1 , -1 , 1 , -1 ] ).reshape( [ 2 , 2 ] )

    Rh2 = np.array( [ 1 , 1 , -1 , -1 ] ).reshape( [ 2 , 2 ] )

    Rh3 = np.array( [ 1 , -1 , -1 , 1 ] ).reshape( [ 2 , 2 ] )

    xlowR = signal.convolve2d( Up2D( x1 ) , Rl1 , boundary='fill' , mode='same' )

    xhighR1 = signal.convolve2d( Up2D( x2 ) , Rh1 , boundary='fill' , mode='same' )

    xhighR2 = signal.convolve2d( Up2D( x3 ) , Rh2 , boundary='fill' , mode='same' )

    xhighR3 = signal.convolve2d( Up2D( x4 ) , Rh3 , boundary='fill' , mode='same' )

    xRecon = xlowR + xhighR1 + xhighR2 + xhighR3

    return xRecon

input = cv2.imread( 'SheppLogan_Phantom.png' , cv2.IMREAD_GRAYSCALE )

input = cv2.resize( input, ( 256 , 256 ) )

Decom = HaarDecom2D( input )

print(Decom.shape)

Recon = HaarRecon2D( Decom )

print(Recon.shape)

cv2.imwrite('Decom.png',Decom)

cv2.imwrite('Recon.png',Recon)
