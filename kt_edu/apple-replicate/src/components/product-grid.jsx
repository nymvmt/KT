import React from 'react'
import Image from 'next/image'
import { ProductCard } from './product-card'

const products = [
  {
    title: "iPhone 15 Pro",
    description: "Titanium. So strong. So light. So Pro.",
    bgColor: "bg-black",
    textColor: "text-white",
    descriptionColor: "text-gray-300",
    buttonVariant: "default"
  },
  {
    title: "iPad Pro",
    description: "Supercharged by M4.",
    bgColor: "bg-gray-100",
    textColor: "text-gray-900",
    descriptionColor: "text-gray-700",
    buttonVariant: "default"
  },
  {
    title: "Apple Watch",
    description: "Your health companion.",
    bgColor: "bg-purple-600",
    textColor: "text-white",
    descriptionColor: "text-purple-100",
    buttonVariant: "default"
  },
  {
    title: "AirPods Pro",
    description: "Adaptive Audio. Now playing.",
    bgColor: "bg-gradient-to-br from-orange-400 to-pink-500",
    textColor: "text-white",
    descriptionColor: "text-orange-100",
    buttonVariant: "default"
  },
  {
    title: "Apple TV 4K",
    description: "The Apple experience. Cinematic.",
    bgColor: "bg-green-600",
    textColor: "text-white",
    descriptionColor: "text-green-100",
    buttonVariant: "default"
  },
  {
    title: "HomePod",
    description: "Profound sound. Intelligent assistant.",
    bgColor: "bg-red-600",
    textColor: "text-white",
    descriptionColor: "text-red-100",
    buttonVariant: "default",
    hasImage: true,
    imageSrc: "/image/apple mouse.png",
    imageAlt: "Apple Mouse"
  }
]

export function ProductGrid() {
  return (
    <section className="py-16 bg-white">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid md:grid-cols-2 gap-8">
          {products.map((product, index) => (
            <ProductCard
              key={index}
              title={product.title}
              description={product.description}
              bgColor={product.bgColor}
              textColor={product.textColor}
              descriptionColor={product.descriptionColor}
              buttonVariant={product.buttonVariant}
              hasImage={product.hasImage}
              imageSrc={product.imageSrc}
              imageAlt={product.imageAlt}
              onLearnMore={() => console.log(`Learn more about ${product.title}`)}
              onBuy={() => console.log(`Buy ${product.title}`)}
            />
          ))}
        </div>
      </div>
    </section>
  )
} 