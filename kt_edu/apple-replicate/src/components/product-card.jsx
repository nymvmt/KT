import React from 'react'
import Image from 'next/image'
import { Button } from './ui/button'

export function ProductCard({ 
  title, 
  description, 
  bgColor = "bg-black", 
  textColor = "text-white", 
  descriptionColor = "text-gray-300",
  buttonVariant = "default",
  hasImage = false,
  imageSrc,
  imageAlt,
  onLearnMore,
  onBuy 
}) {
  return (
    <div className={`${bgColor} ${textColor} rounded-3xl p-8 text-center min-h-[400px] flex flex-col justify-center relative overflow-hidden`}>
      {hasImage && (
        <div className="absolute top-4 right-4 w-16 h-16 opacity-20">
          <Image src={imageSrc} alt={imageAlt} width={64} height={64} className="w-full h-full object-contain" />
        </div>
      )}
      <h3 className="text-4xl font-semibold mb-4">{title}</h3>
      <p className={`text-xl mb-6 ${descriptionColor}`}>{description}</p>
      <div className="flex justify-center gap-4">
        <Button 
          className="px-6 py-2 rounded-full" 
          variant={buttonVariant}
          onClick={onLearnMore}
        >
          Learn more
        </Button>
        <Button
          variant="outline"
          className="px-6 py-2 rounded-full bg-transparent"
          onClick={onBuy}
        >
          Buy
        </Button>
      </div>
    </div>
  )
} 