"use client"

import { useState } from "react"
import { Navigation } from "../components/navigation"
import { HeroSection } from "../components/hero-section"
import { MacBookSection } from "../components/macbook-section"
import { ProductGrid } from "../components/product-grid"
import { CarouselSection } from "../components/carousel-section"
import { FlowingServices } from "../components/flowing-services"
import { Footer } from "../components/footer"
import { navigationMenus } from "../data/navigation-menus"

export default function Page() {
  const [isScrollPaused, setIsScrollPaused] = useState(false)
  const [hoveredMenu, setHoveredMenu] = useState(null)

  const toggleScroll = () => {
    setIsScrollPaused(!isScrollPaused)
  }

  const navItems = [
    { name: "Store", key: "store", href: "/store" },
    { name: "Mac", key: "mac", href: "/mac" },
    { name: "iPad", key: "ipad", href: "/ipad" },
    { name: "iPhone", key: "iphone", href: "/iphone" },
    { name: "Watch", key: "watch", href: "/watch" },
    { name: "Vision", key: "vision", href: "/vision" },
    { name: "AirPods", key: "airpods", href: "/airpods" },
    { name: "TV & Home", key: "tv-home", href: "/tv-home" },
    { name: "Entertainment", key: "entertainment", href: "/entertainment" },
    { name: "Accessories", key: "accessories", href: "/accessories" },
    { name: "Support", key: "support", href: "/support" },
  ]

  return (
    <div className="min-h-screen bg-white">
      {/* Navigation */}
      <Navigation 
        navItems={navItems} 
        navigationMenus={navigationMenus} 
        hoveredMenu={hoveredMenu} 
        setHoveredMenu={setHoveredMenu} 
      />

      {/* Hero Section */}
      <HeroSection />

      {/* Spacing between Hero and MacBook Air sections */}
      <div className="h-16 bg-white"></div>

      {/* MacBook Air Section */}
      <MacBookSection />

      {/* Product Grid */}
      <ProductGrid />

      {/* Carousel Section */}
      <CarouselSection />

      {/* Flowing Services Section */}
      <FlowingServices isScrollPaused={isScrollPaused} toggleScroll={toggleScroll} />

      {/* Footer */}
      <Footer />
    </div>
  )
}
