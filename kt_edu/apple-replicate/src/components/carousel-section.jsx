import React from 'react'
import { Button } from './ui/button'

const carouselProducts = [
  { name: "MacBook Pro", color: "bg-gray-800", textColor: "text-white" },
  { name: "iMac", color: "bg-blue-500", textColor: "text-white" },
  { name: "Mac Studio", color: "bg-gray-700", textColor: "text-white" },
  { name: "Mac Pro", color: "bg-black", textColor: "text-white" },
  { name: "iPad Air", color: "bg-purple-500", textColor: "text-white" },
  { name: "iPad mini", color: "bg-pink-500", textColor: "text-white" },
  { name: "Apple Pencil", color: "bg-orange-500", textColor: "text-white" },
  { name: "Magic Keyboard", color: "bg-indigo-600", textColor: "text-white" },
  { name: "Studio Display", color: "bg-teal-600", textColor: "text-white" },
  { name: "Pro Display XDR", color: "bg-red-700", textColor: "text-white" },
]

export function CarouselSection() {
  return (
    <section className="py-16 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4">
        <h2 className="text-3xl font-semibold text-center mb-12 text-gray-900">Explore Apple Products</h2>
        <div className="flex gap-6 overflow-x-auto pb-4 scrollbar-hide">
          {carouselProducts.map((product, index) => (
            <div
              key={index}
              className={`${product.color} ${product.textColor} rounded-2xl p-6 min-w-[280px] h-[200px] flex flex-col justify-center items-center text-center flex-shrink-0 hover:scale-105 transition-transform duration-200 cursor-pointer`}
            >
              <h3 className="text-2xl font-semibold mb-2">{product.name}</h3>
              <p className="text-sm opacity-80 mb-4">Discover more</p>
              <Button
                variant="outline"
                className="border-current text-current hover:bg-current hover:text-gray-900 px-4 py-1 rounded-full text-sm bg-transparent"
              >
                View
              </Button>
            </div>
          ))}
        </div>
      </div>
    </section>
  )
} 