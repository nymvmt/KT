import React from 'react'
import Image from 'next/image'

export function HeroBubble({ 
  children, 
  width = "w-80", 
  className = "",
  showArrow = true 
}) {
  return (
    <div className="relative">
      <div className={`bg-blue-500 text-white px-8 py-6 rounded-3xl ${width} mx-auto shadow-xl ${className}`}>
        {children}
      </div>
      {showArrow && (
        <div className="absolute -bottom-3 left-1/2 transform -translate-x-1/2 w-6 h-6 bg-blue-500 rotate-45"></div>
      )}
    </div>
  )
}

export function ProductImage({ src, alt, width, height, className = "" }) {
  return (
    <div className={`w-${width} h-${height} flex items-center justify-center`}>
      <Image 
        src={src} 
        alt={alt} 
        width={parseInt(width) * 4} 
        height={parseInt(height) * 4} 
        className={`w-full h-full object-contain ${className}`} 
      />
    </div>
  )
} 