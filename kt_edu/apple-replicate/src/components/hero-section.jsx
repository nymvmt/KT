import React from 'react'
import Image from 'next/image'
import { Button } from './ui/button'
import { HeroBubble, ProductImage } from './hero-bubble'

export function HeroSection() {
  return (
    <section className="relative bg-gray-100 min-h-[700px] flex items-center justify-center overflow-hidden">
      {/* Background Pattern with AirPods */}
      <div className="absolute inset-0">
        {[
          { top: "top-10", left: "left-10", size: "w-16 h-16", opacity: "opacity-60" },
          { top: "top-32", left: "right-20", size: "w-20 h-20", opacity: "opacity-40" },
          { top: "bottom-20", left: "left-1/4", size: "w-12 h-12", opacity: "opacity-50" },
          { top: "top-1/2", left: "right-10", size: "w-14 h-14", opacity: "opacity-30" },
          { top: "bottom-32", left: "right-1/3", size: "w-18 h-18", opacity: "opacity-45" },
          { top: "top-20", left: "left-1/3", size: "w-10 h-10", opacity: "opacity-35" },
          { top: "top-1/4", left: "left-1/2", size: "w-14 h-14", opacity: "opacity-40" },
          { top: "bottom-1/4", left: "left-20", size: "w-16 h-16", opacity: "opacity-35" },
          { top: "top-3/4", left: "right-1/4", size: "w-12 h-12", opacity: "opacity-50" },
        ].map((position, index) => (
          <div key={index} className={`absolute ${position.top} ${position.left} ${position.size} ${position.opacity}`}>
            <Image src="/image/airpod.png" alt="AirPods" width={64} height={64} className="w-full h-full object-contain" />
          </div>
        ))}
      </div>

      <div className="relative z-10 text-center max-w-3xl mx-auto px-4">
        {/* Main Promotion Bubbles */}
        <div className="flex flex-col items-center space-y-6 mb-8">
          {/* First Large Bubble - Buy Mac or iPad */}
          <HeroBubble width="w-96">
            <div className="flex items-center justify-center gap-4 mb-3">
              <div className="text-left">
                <div className="text-3xl font-bold leading-tight">Buy Mac</div>
                <div className="text-3xl font-bold leading-tight">or iPad</div>
                <div className="text-3xl font-bold leading-tight">for college</div>
              </div>
              <div className="flex flex-col gap-2">
                <ProductImage src="/image/macbookair_front.png" alt="MacBook Air" width="20" height="14" />
                <ProductImage src="/image/ipad.png" alt="iPad" width="16" height="20" />
              </div>
            </div>
          </HeroBubble>

          {/* Second Bubble - Education Savings */}
          <HeroBubble width="w-80">
            <p className="text-lg font-medium">with education savings</p>
          </HeroBubble>

          {/* Third Bubble - AirPods Choice */}
          <HeroBubble width="w-80">
            <div className="flex items-center gap-3 mb-2">
              <span className="text-2xl">🎓</span>
            </div>
            <div className="text-2xl font-bold leading-tight mb-1">Choose</div>
            <div className="text-2xl font-bold leading-tight mb-1">AirPods or</div>
            <div className="text-2xl font-bold leading-tight mb-1">an eligible</div>
            <div className="text-2xl font-bold leading-tight">accessory*</div>
          </HeroBubble>
        </div>

        <Button
          variant="outline"
          className="bg-white/90 backdrop-blur-sm border-blue-500 text-blue-500 hover:bg-blue-50 px-8 py-3 rounded-full font-medium transition-all duration-200 text-lg"
        >
          Shop
        </Button>
      </div>
    </section>
  )
} 