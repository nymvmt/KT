import React from 'react'
import Image from 'next/image'
import { Button } from './ui/button'

export function MacBookSection() {
  return (
    <section className="bg-gradient-to-b from-blue-50 to-blue-100 pt-16 pb-20">
      <div className="max-w-6xl mx-auto px-4 text-center">
        <h2 className="text-5xl md:text-6xl font-semibold text-gray-900 mb-4 tracking-tight">MacBook Air</h2>
        <p className="text-xl text-gray-700 mb-2 font-medium">Sky blue color.</p>
        <p className="text-xl text-gray-700 mb-8 font-medium">Sky high performance with M4.</p>

        <div className="flex justify-center gap-4 mb-12">
          <Button className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-full font-medium transition-all duration-200">
            Learn more
          </Button>
          <Button
            variant="outline"
            className="border-blue-500 text-blue-500 hover:bg-blue-50 px-6 py-3 rounded-full font-medium transition-all duration-200 bg-transparent"
          >
            Buy
          </Button>
        </div>

        <div className="relative max-w-4xl mx-auto">
          <Image
            src="/image/macbookair.png"
            alt="MacBook Air in sky blue"
            width={800}
            height={500}
            className="w-full h-auto drop-shadow-2xl"
          />
        </div>
      </div>
    </section>
  )
} 